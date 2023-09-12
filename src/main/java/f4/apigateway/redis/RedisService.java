package f4.apigateway.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
  private final RedisTemplate<String, String> blackListTemplate;

  public boolean hasBlackList(String key) {
    return Boolean.TRUE.equals(blackListTemplate.hasKey(key));
  }
}