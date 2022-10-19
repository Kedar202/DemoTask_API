package com.studentdemo.studentdemo.schedular;

import com.studentdemo.studentdemo.dao.UserRepository;
import com.studentdemo.studentdemo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class AdminSchedular {

    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "0 0 */1 * * *")
    public void timeschedular() {
        try {
            List<UserEntity> users = userRepository.getInActiveUser();
            for (UserEntity userEntity : users) {
                Date lastDate = userEntity.getLastLoginDate();
                Date date = getupdateDate(lastDate, 24);
                if (date.before(new Date())) {
                    userEntity.setAttempts(0);
                    userEntity.setStatus("ACTIVE");
                    userEntity.setLastLoginDate(null);
                    userRepository.save(userEntity);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Date getupdateDate(Date lastDate, Integer hour) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }
}
