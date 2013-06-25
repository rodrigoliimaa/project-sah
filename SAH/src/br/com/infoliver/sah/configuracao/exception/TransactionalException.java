package br.com.infoliver.sah.configuracao.exception;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public @interface TransactionalException {}