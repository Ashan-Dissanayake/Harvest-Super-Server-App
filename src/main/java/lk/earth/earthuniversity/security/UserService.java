//package lk.earth.earthuniversity.security;
//
//import lk.earth.earthuniversity.dao.UserDao;
//import lk.earth.earthuniversity.entity.User;
//import lk.earth.earthuniversity.entity.Userrole;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    final UserDao userdao;
//
//    @Autowired
//    public UserService(UserDao userdao) {
//        this.userdao = userdao;
//    }
//
//    public User getByUsername(String username) {
//
//        User user;
//        if ("AdminEUC".equals(username)) {
//            user = new User();
//            user.setUsername(username);
//        } else {
//            user = userdao.findByUsername(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found with username: " + username);
//            }
//        }
//        return user;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        if (username.equals("AdminEUC")) {
//            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//            return org.springframework.security.core.userdetails.User
//                    .withUsername("AdminEUC")
//                    .password(new BCryptPasswordEncoder().encode("Admin1234"))
//                    .authorities(authorities)
//                    .accountExpired(false)
//                    .accountLocked(false)
//                    .credentialsExpired(false)
//                    .disabled(false)
//                    .build();
//        } else {
//
//            User user = userdao.findByUsername(username);
//
//            if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);
//
//            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//
//            List<Userrole> userroles = (List<Userrole>) user.getUserroles();
//
//            for (Userrole u : userroles) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole().getName().toUpperCase()));
//            }
//
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .authorities(authorities)
//                    .accountExpired(false)
//                    .accountLocked(false)
//                    .credentialsExpired(false)
//                    .disabled(false)
//                    .build();
//        }
//    }
//}
//

package lk.earth.earthuniversity.security;

import lk.earth.earthuniversity.dao.UserDao;
import lk.earth.earthuniversity.entity.Privilege;
import lk.earth.earthuniversity.entity.User;
import lk.earth.earthuniversity.entity.Userrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    final UserDao userdao;

    @Autowired
    public UserService(UserDao userdao) {
        this.userdao = userdao;
    }

    public User getByUsername(String username){

        User user = new User();

        if ("AdminEUC".equals(username)){

            user.setUsername(username);

        }else {
            user = userdao.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }

        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("AdminEUC")) {

            Set<SimpleGrantedAuthority> authorities = new HashSet<>();

            authorities.add(new SimpleGrantedAuthority("user-select"));
            authorities.add(new SimpleGrantedAuthority("user-delete"));
            authorities.add(new SimpleGrantedAuthority("user-update"));
            authorities.add(new SimpleGrantedAuthority("user-insert"));

            authorities.add(new SimpleGrantedAuthority("privilege-select"));
            authorities.add(new SimpleGrantedAuthority("privilege-delete"));
            authorities.add(new SimpleGrantedAuthority("privilege-update"));
            authorities.add(new SimpleGrantedAuthority("privilege-insert"));

            authorities.add(new SimpleGrantedAuthority("employee-select"));
            authorities.add(new SimpleGrantedAuthority("employee-delete"));
            authorities.add(new SimpleGrantedAuthority("employee-update"));
            authorities.add(new SimpleGrantedAuthority("employee-insert"));

            authorities.add(new SimpleGrantedAuthority("operations-select"));
            authorities.add(new SimpleGrantedAuthority("operations-delete"));
            authorities.add(new SimpleGrantedAuthority("operations-update"));
            authorities.add(new SimpleGrantedAuthority("operations-insert"));

            authorities.add(new SimpleGrantedAuthority("item-select"));
            authorities.add(new SimpleGrantedAuthority("item-delete"));
            authorities.add(new SimpleGrantedAuthority("item-update"));
            authorities.add(new SimpleGrantedAuthority("item-insert"));

            authorities.add(new SimpleGrantedAuthority("supplier-select"));
            authorities.add(new SimpleGrantedAuthority("supplier-delete"));
            authorities.add(new SimpleGrantedAuthority("supplier-update"));
            authorities.add(new SimpleGrantedAuthority("supplier-insert"));


            return org.springframework.security.core.userdetails.User
                    .withUsername("AdminEUC")
                    .password(new BCryptPasswordEncoder().encode("Admin1234"))
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }
        else {

            User user = userdao.findByUsername(username);

            if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);

            Set<SimpleGrantedAuthority> authorities = new HashSet<>();

            List<Userrole> userroles = (List<Userrole>) user.getUserroles();

            for(Userrole u : userroles){
                List<Privilege> privileges = (List<Privilege>) u.getRole().getPrivileges();
                for (Privilege p:privileges){
                    authorities.add(new SimpleGrantedAuthority(p.getAuthority()));
                }
            }

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }
    }
}
