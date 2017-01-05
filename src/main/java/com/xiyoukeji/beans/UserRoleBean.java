package com.xiyoukeji.beans;

import com.xiyoukeji.entity.Authority;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class UserRoleBean {
    private Integer id;
    private int type;
    private String roleName;
    private RoleAuthorityBean authority;

    public RoleAuthorityBean getAuthority() {
        return authority;
    }

    public void setAuthority(RoleAuthorityBean authority) {
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
