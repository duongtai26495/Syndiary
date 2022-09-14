package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.Token;

public interface BlacklistTokenService {
    Boolean isExist(Token token);
}
