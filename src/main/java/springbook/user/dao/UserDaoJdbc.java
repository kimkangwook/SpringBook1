package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private RowMapper<User> userMapper =
//            new RowMapper<User>() {
//                @Override
//                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    User user = new User();
//                    user.setId(rs.getString("id"));
//                    user.setPassword(rs.getString("password"));
//                    user.setName(rs.getString("name"));
//                    return user;
//                }
//            };

            (rs, i) -> {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setLevel(Level.valueOf(rs.getInt("Level")));
                user.setLogin(rs.getInt("login"));
                user.setRecommend(rs.getInt("recommend"));
                user.setEmail(rs.getString("email"));

                return user;
            };


    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id,name,password,level,login,recommend,email) values(?,?,?,?,?,?,?)", user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getEmail());
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?", this.userMapper, new Object[]{id});
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);

    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("update users set name=?, password=?, level=?, login=?, recommend=?, email=? where id=?",
                user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(),user.getEmail(),user.getId());

    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", this.userMapper);

    }


}
