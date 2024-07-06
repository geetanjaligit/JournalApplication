package com.anshu.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.anshu.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

}
