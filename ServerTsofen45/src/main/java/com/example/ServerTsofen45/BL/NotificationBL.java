package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ServerTsofen45.Repo.NotificationRepository;


@Service
public class NotificationBL {
    @Autowired
    NotificationRepository NotificationRepository;

}