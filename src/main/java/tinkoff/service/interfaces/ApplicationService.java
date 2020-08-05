package tinkoff.service.interfaces;

import tinkoff.entity.Application;

public interface ApplicationService {
    Application findLatestByContactId(Integer contactId);
}
