package com.example.demo;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller

public class UserController {
@Autowired
	private UserRepository userRepository;
@Autowired
	UserService userService;

@RequestMapping(value = "/all", method = RequestMethod.GET)
//public String helloWorld(Model model) {
    //model.addAttribute("message", "Hello World!!");

public String getAllUsers(@Validated User user,  BindingResult result,
							@PageableDefault(size = 10) Pageable pageable,
							Model model) {


	//Page<User> page = userService.searchUserAll(pageable);
	Page<User> page = userRepository.find(pageable);
	model.addAttribute("users", page);

	return "index";
}


@RequestMapping(value = "/Search", method = RequestMethod.POST)
public String getSearchUsers(@Validated User user,  BindingResult result,
							  @PageableDefault(size = 10) Pageable pageable,
							  @RequestParam(name = "search") String str1, Model model) {

	Page<User> page = userService.searchUser(str1,pageable);
	model.addAttribute("users", page);
	return"index";
}
/**
 * ユーザー新規登録画面を表示
 * @param model Model
 * @return ユーザー情報一覧画面
 */
@GetMapping(value = "/add")
public String displayAdd(Model model) {
  model.addAttribute("userRequest", new UserRequest());
  return "add";
}


/**
 * ユーザー新規登録
 * @param userRequest リクエストデータ
 * @param model Model
 * @return ユーザー情報一覧画面
 */
@RequestMapping(value = "/usercreate", method = RequestMethod.POST)
public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {

	 // ユーザー情報の登録
    userService.create(userRequest);
    return "redirect:/index";
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
    model.addAttribute("user", user);
    return "UpdateU";
}
/**
 * ユーザー更新
 * @param userRequest リクエストデータ
 * @param model Model
 * @return ユーザー情報詳細画面
 */
@RequestMapping(value="/userupdate", method=RequestMethod.POST)
public String update(@Validated @ModelAttribute User user, BindingResult result, Model model) {
    // ユーザー情報の更新
    userService.update(user);
    return String.format("redirect:/user/%d", user.getId());
}
@GetMapping("/user/{id}/edit")
public String displayEdit2(@PathVariable Long id, Model model) {
User user = userService.findById(id);

user.setId(user.getId());
user.setName(user.getName());
user.setAddress(user.getAddress());
user.setTel(user.getTel());
model.addAttribute("user", user);

List<Category> category =userRepository.findCategoryAll();
model.addAttribute("category", category);


return "UpdateU";
}
	@RequestMapping(value="/useredit", method=RequestMethod.GET)
	//@RequestMapping(value="/useredit", method=RequestMethod.POST)
	public String userEdit(@Validated @ModelAttribute User user,Model model,@RequestParam String name,@RequestParam String address, @RequestParam String tel) {
	//public String userEdit(@Validated @ModelAttribute User user,Model model) {

	//User user = userService.findById(id);
		user.setId(user.getId());
		user.setName(name);
		user.setAddress(address);
		user.setTel(tel);
	/*
	user.setId(user.getId());
	user.setName(user.getName());
	user.setAddress(user.getAddress());
	user.setTel(user.getTel());
	*/
	model.addAttribute("user", user);
	return "userEdit";
	}
}
