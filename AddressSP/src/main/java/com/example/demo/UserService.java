package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackOn = Exception.class)

public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	SearchService searchService;
	/**
	   * ユーザー情報 全検索
	   * @return 検索結果
	   */
	public Page<User> searchUserAll(Pageable pageable) {
		  Page<User> page = userRepository.findAllByFreeWord(pageable);
	    return page;
	  }
	/**
	   * ユーザー情報 部分検索
	   * @return 検索結果
	   */
	public Page<User> searchUser(String name, String add ,String tel ,Pageable pageable) {

		  Specification<User> spec = Specification.where(SearchService.UserNameContains(name))
				  									.and(SearchService.UserAddContains(add))
				  									.and(SearchService.UserTelContains(tel))
				  									;

		  //Page<User> page = userRepository.findAllByFreeSearch(str1,pageable, Specification.where(SearchService.UserNameContains("高橋")));
		  Page<User> page = userRepository.findAll(spec,pageable);

	    return page;
	  }
	  /**
	   * ユーザー情報 新規登録
	   * @param user ユーザー情報
	   */
	public void create(UserRequest userRequest) {
		User user = new User();
	    user.setName(userRequest.getName());
	    user.setAddress(userRequest.getAddress());
	    user.setTel(userRequest.getTel());
	    user.setDelete_flg("0");
	    userRepository.save(user);
	  }


	  /**
     * ユーザー情報 主キー検索
     * @return 検索結果
     */
    public User findById(long id) {
        return userRepository.findById(id).get();
    }
    /**
     * ユーザー情報 更新
     * @param user ユーザー情報
     */
    public void update(User user) {

    	System.out.println("--------------");
        System.out.println(user.getId());
        System.out.println("--------------");
        User user2 = findById(user.getId());


        System.out.println(user2.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getTel());
        System.out.println(user2.getDelete_flg());
        System.out.println("--------------");
        user.setDelete_flg("0");
        userRepository.save(user);
    }

}
