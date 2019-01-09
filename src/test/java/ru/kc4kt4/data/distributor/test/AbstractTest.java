package ru.kc4kt4.data.distributor.test;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase
public abstract class AbstractTest {
}
