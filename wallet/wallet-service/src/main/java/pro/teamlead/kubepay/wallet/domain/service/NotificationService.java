package pro.teamlead.kubepay.wallet.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    public void notify(String user, String message) {
        log.info("Message to: {}, text: {}", user, message);
    }
}
