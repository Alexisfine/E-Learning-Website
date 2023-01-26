package com.alex.elearning.utils;

import com.github.ksuid.KsuidGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class KsuidIdentiferGenerator implements IdentifierGenerator {
    @Override
    public String generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return KsuidGenerator.generate();
    }
}
