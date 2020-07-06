package com.example.demo;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SearchService {


    // 商品名を含むものを検索
    public static Specification<User> UserNameContains(String UserName) {
        return StringUtils.isEmpty(UserName) ? null : new Specification<User>() {
          @Override
          public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("name"), "%" + UserName + "%");
          }
        };
    }
    public static Specification<User> UserAddContains(String UserAdd) {
        return StringUtils.isEmpty(UserAdd) ? null : new Specification<User>() {
          @Override
          public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("address"), "%" + UserAdd + "%");
          }
        };
    }
    public static Specification<User> UserTelContains(String UserTel) {
        return StringUtils.isEmpty(UserTel) ? null : new Specification<User>() {
          @Override
          public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("tel"), "%" + UserTel + "%");
          }
        };
    }

}