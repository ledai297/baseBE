package vn.sapo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.sapo.domain.ActionLog;
import vn.sapo.repository.ActionLogRepository;

@Service
public class ActionLogService {
    @Autowired
    private ActionLogRepository actionLogRepository;
    @Async
    public void save(ActionLog actionLog){
        actionLogRepository.save(actionLog);
    }
}
