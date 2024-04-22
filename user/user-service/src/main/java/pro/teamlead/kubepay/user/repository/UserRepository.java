package pro.teamlead.kubepay.user.repository;

import pro.teamlead.kubepay.user.domain.model.User;

public interface UserRepository {

    User findByUser(String user);

    User save(User user);
}
