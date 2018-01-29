package service;

import bean.UserDengLu;
import mapper.UserDengLuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午8:59 2018/1/10
 * @Modified By:
 */
@Service
public class UserDengLuServiceImpl implements UserDengLuService{
    @Autowired
    private UserDengLuMapper userDengLuMapper;
    private UserDengLu userByuser;

    @Override
    public UserDengLu findUserByuser(UserDengLu userDengLu) {
            userByuser = userDengLuMapper.findUserByuser ( userDengLu );

        return userByuser;
    }

}
