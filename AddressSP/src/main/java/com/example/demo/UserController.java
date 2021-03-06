package com.example.demo;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller

public class UserController {
@Autowired
	private UserRepository userRepository;
@Autowired
	private UserRepositoryCategory userRepositoryCategory;
@Autowired
	UserService userService;
@Autowired
	SearchService searchService;

@RequestMapping("/page1")
public String page1(RedirectAttributes attributes) {
	User attr = new User();
	attr.setName("testname");
	attr.setAddress("testadd");
  attributes.addFlashAttribute("attr1",attr);
  return "redirect:/page2";
}

@RequestMapping("/page2")
public String page2(@ModelAttribute("attr1") User user, Model model) {
  // attr1 == "flashdata"
  model.addAttribute("attr");
  return "index";
}

@RequestMapping(value = "/all", method = RequestMethod.GET)
//public String helloWorld(Model model) {
    //model.addAttribute("message", "Hello World!!");

public String getAllUsers(@Validated User user,  BindingResult result,
							@PageableDefault(size = 10) Pageable pageable,
							Model model) {


	//Page<User> page = userService.searchUserAll(pageable);
	Page<User> wordpage = userRepository.find(pageable);
	PageWrapper<User> page = new PageWrapper<User>(wordpage, "/all");
	model.addAttribute("users", page.getContent());
	model.addAttribute("page", page);

	return "index";
}


@RequestMapping(value = "/Search", method = RequestMethod.GET)
public String getSearchUsers(@Validated User user,  BindingResult result,
							  @PageableDefault(size = 3) Pageable pageable,
							  @RequestParam(name = "searchName",required=false) String name,
							  @RequestParam (name = "searchAdd",required=false) String add,
							  @RequestParam (name = "searchTel",required=false) String tel,
							  Model model) {

	Page<User> wordpage = userService.searchUser(name,add,tel,pageable);
	PageWrapper<User> page = new PageWrapper<User>(wordpage, "/Search");
	model.addAttribute("users", page.getContent());
	model.addAttribute("page", page);
	model.addAttribute("searchName", name);
	model.addAttribute("searchAdd", add);
	model.addAttribute("searchTel", tel);


	return"index";
}
/**
 * ユーザー新規登録画面を表示
 * @param model Model
 * @return ユーザー情報一覧画面
 */
@GetMapping(value = "/add")
public String displayAdd(Model model) {

  List<Category> category =userRepository.findCategoryAll();
  model.addAttribute("userRequest", new UserRequest());
  model.addAttribute("category",category);
  return "add";
}


/**
 * ユーザー新規登録
 * @param userRequest リクエストデータ
 * @param model Model
 * @return ユーザー情報一覧画面
 */
@RequestMapping(value = "/usercreate", method = RequestMethod.POST)
//public String create(@Validated @ModelAttribute User user,Model model,@RequestParam String name,@RequestParam String address, @RequestParam String tel, @RequestParam Long categoryid) {
public String create_(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {


    if (result.hasErrors()) {
        List<String> errorList = new ArrayList<String>();
        for (ObjectError error : result.getAllErrors()) {
          errorList.add(error.getDefaultMessage());
        }
        List<Category> categoryall =userRepository.findCategoryAll();
        model.addAttribute("validationError", errorList);
        model.addAttribute("category",categoryall);
        return "add";
      }
    Category category = userRepositoryCategory.findById(userRequest.getCategoryid()).get();
	model.addAttribute("user", userRequest);
	model.addAttribute("category",category);
    //userService.create(userRequest);
    return "addCheck";
  }


/**
 * ユーザー編集画面を表示
 * @param id 表示するユーザーID
 * @param model Model
 * @return ユーザー編集画面
 */
//@RequestMapping(value="/edit", method=RequestMethod.POST)
@GetMapping("/edit/{id}")
public String displayEdit(@PathVariable Long id, Model model) {
    User user = userService.findById(id);

    user.setId(user.getId());
    user.setName(user.getName());
    user.setAddress(user.getAddress());
    user.setTel(user.getTel());
    user.setCategoryid(user.getCategoryid());
    model.addAttribute("user", user);
    return "UpdateU";
}
/**
 * ユーザー更新
 * @param userRequest リクエストデータ
 * @param model Model
 * @return ユーザー情報詳細画面
 */
@RequestMapping(value="/userAddCommit", method=RequestMethod.POST)
public String createcommit(@Validated @ModelAttribute UserRequest user, BindingResult result, Model model) {


    // ユーザー情報の更新
	userService.create(user);
    return "redirect:all";
    //return String.format("redirect:/user/%d", user.getId());
}

@RequestMapping(value="/userupdate", method=RequestMethod.POST)
public String update(@Validated @ModelAttribute User user, BindingResult result, Model model) {
    // ユーザー情報の更新

    userService.update(user);
    return "redirect:all";
    //return String.format("redirect:/user/%d", user.getId());
}
/**
 * ユーザー更新
 * @param userRequest リクエストデータ
 * @param model Model
 * @return ユーザー編集画面
 */
@GetMapping("/user/{id}/edit")
public String displayEdit2(@PathVariable Long id, Model model) {
User user = userService.findById(id);

user.setId(user.getId());
user.setName(user.getName());
user.setAddress(user.getAddress());
user.setTel(user.getTel());
user.setCategoryid(user.getCategoryid());
model.addAttribute("user", user);

List<Category> category =userRepository.findCategoryAll();
List<Category> category2 =userRepository.findCategoryAll();
model.addAttribute("category", category);
model.addAttribute("category2", category2);


return "UpdateU";
}

@GetMapping("/user/edit")
public String displayEdit3(@PathVariable Long id, Model model) {
User user = userService.findById(id);

user.setId(user.getId());
user.setName(user.getName());
user.setAddress(user.getAddress());
user.setTel(user.getTel());
user.setCategoryid(user.getCategoryid());
model.addAttribute("user", user);

List<Category> category =userRepository.findCategoryAll();
List<Category> category2 =userRepository.findCategoryAll();
model.addAttribute("category", category);
model.addAttribute("category2", category2);


return "UpdateU";
}



/**
 * ユーザー更新
 * @param userRequest リクエストデータ
 * @param model Model
 * @return ユーザー編集確認画面
 */
	@RequestMapping(value="/useredit", method=RequestMethod.GET)
	//@RequestMapping(value="/useredit", method=RequestMethod.POST)
	public String userEdit(@Validated @ModelAttribute User user,Model model,@RequestParam String name,@RequestParam String address, @RequestParam String tel, @RequestParam Long categoryid) {
	//public String userEdit(@Validated @ModelAttribute User user,Model model) {

	//User user = userService.findById(id);
		user.setId(user.getId());
		user.setName(name);
		user.setAddress(address);
		user.setTel(tel);
		user.setCategoryid(categoryid);
		Category category = userRepositoryCategory.findById(categoryid).get();
		Category category2 = userRepositoryCategory.findById(categoryid).get();
		//User category = userRepository.findById(categoryid).get();
	/*
	user.setId(user.getId());
	user.setName(user.getName());
	user.setAddress(user.getAddress());
	user.setTel(user.getTel());
	*/
	model.addAttribute("user", user);
	model.addAttribute("category", category);
	model.addAttribute("category2", category2);
	return "userEdit";
	}
}


