package com.isacore.acta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.acta.model.MeetingMinute;

@Repository
public interface IMeetingMinuteRepo extends JpaRepository<MeetingMinute, String>{

}
