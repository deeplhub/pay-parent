package com.xh.pay.polling.dao.rep;

import com.xh.pay.polling.entity.UserPayConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public interface UserPayConfigRepDao extends JpaRepository<UserPayConfig, Long> {

    @Query("select upc from UserPayConfig upc where upc.userNo=?1")
    UserPayConfig getByUserNo(String merchantNo);
}
