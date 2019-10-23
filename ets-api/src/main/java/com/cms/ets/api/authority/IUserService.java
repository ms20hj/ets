package com.cms.ets.api.authority;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.authority.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 * @author cms
 * @since 2019-07-22
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询
     * @param page 分页信息
     * @param name 用户名/真实姓名
     * @return IPage<User>
     */
    IPage<User> page(IPage<User> page, String name);

    /**
     * 根据名称查询
     * @param name 名称
     * @return User
     * @date 2019年10月16日09:54:17
     */
    User getByUserName(String userName);

    /**
     * 判断名称是否已存在
     * @param userName 用户名
     * @param id 主键
     * @return boolean 存在true，不存在false
     */
    boolean checkNameExist(String userName, String id);

    /**
     * 查询用户集合，用户对象只初始化id，userName
     * @return List<User>
     * @date 2019年10月22日11:37:05
     */
    List<User> getSimpleUserList();
}
