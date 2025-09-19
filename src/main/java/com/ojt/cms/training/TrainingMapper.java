package com.ojt.cms.training;

import com.ojt.cms.user.User;

public class TrainingMapper {
	public static Training toEntity(TrainingDTO dto) {
		return Training.builder()
				.trainId(dto.getTrainId())
				.course(dto.getCourse())
				.trainName(dto.getTrainName())
				.startTrain(dto.getStartTrain())
				.endTrain(dto.getEndTrain())
				.status(dto.getStatus())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public static TrainingDTO toDTO(Training entity) {
		return TrainingDTO.builder()
				.trainId(entity.getTrainId())
				.course(entity.getCourse())
				.trainName(entity.getTrainName())
				.startTrain(entity.getStartTrain())
				.endTrain(entity.getEndTrain())
				.status(entity.getStatus())
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
}
