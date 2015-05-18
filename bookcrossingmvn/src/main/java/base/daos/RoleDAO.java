package base.daos;

import base.entities.Role;

import java.util.List;

/**
 * Created by Vadim on 29.03.2015.
 */
public interface RoleDAO {
    Role createRole(String roleName);
    void deleteRole(Long id);
    void updateRole(Role roleDetached);
    Role getRoleById(Long id);
    List<Role> getRoleNameLike(String name);
    List<Role> getAllRoles();
//    List<User> getUsersByRole(Long roleId);
}
