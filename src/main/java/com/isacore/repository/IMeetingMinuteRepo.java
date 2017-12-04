package com.isacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.model.MeetingMinute;

@Repository
public interface IMeetingMinuteRepo extends JpaRepository<MeetingMinute, String>{

}
