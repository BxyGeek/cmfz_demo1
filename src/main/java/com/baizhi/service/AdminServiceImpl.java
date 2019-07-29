package com.baizhi.service;

import com.baizhi.api.BaseApiService;
import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 类描述信息 (管理员接口实现)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 14:16
 * @version: V_1.0.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl extends BaseApiService implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    /**
     * 方法描述: (管理员登陆)
     * @param admin
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> login(Admin admin, HttpSession session,String enCode) {
        try {
            String validateCode = (String) session.getAttribute("validateCode");
            if (enCode == null || StringUtils.equals("",enCode)){
                return setResultError("验证码为空");
            }if (!StringUtils.equals(validateCode,enCode)){
                return setResultError("验证码错误");
            }
            Admin byPrimaryKey = adminDAO.selectByPrimaryKey(admin.getAdminName());

            if (byPrimaryKey == null){
                return setResultError("帐号错误");
            }if (!StringUtils.equals(byPrimaryKey.getPassword(),admin.getPassword())){
                return setResultError("密码错误");
            }
            //将管理员存入session中
            session.setAttribute("admin",byPrimaryKey);
            return setResultSuccessData(byPrimaryKey);
        }catch (Exception e){
            return setResultError("系统错误，请稍后再试");
        }
    }

    @Override
    public Admin selectAdminByUsername(String username) {
        Admin admin = new Admin();
        admin.setAdminName(username);
        Admin one = adminDAO.selectOne(admin);
        log.info("从数据库查询出的管理员对象：         "+one);
        return  one;
    }
}
