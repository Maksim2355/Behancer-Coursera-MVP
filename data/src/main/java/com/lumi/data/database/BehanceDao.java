package com.lumi.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lumi.domain.model.project.Cover;
import com.lumi.domain.model.project.Owner;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.model.user.Image;
import com.lumi.domain.model.user.User;

import java.util.List;


@Dao
public interface BehanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCovers(List<Cover> covers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwners(List<Owner> owners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Image image);

    @Query("select * from project")
    List<Project> getProjects();

    @Query("select * from cover where project_id = :projectId")
    Cover getCoverFromProject(int projectId);

    @Query("select * from owner where project_id = :projectId")
    List<Owner> getOwnersFromProject(int projectId);

    @Query("select * from user where username = :userName")
    User getUserByName(String userName);

    @Query("select * from image where user_id = :userId")
    Image getImageFromUser(int userId);

    @Query("delete from owner")
    void clearOwnerTable();

    @Query("delete from cover")
    void clearCoverTable();

    @Query("delete from image")
    void clearImageTable();

    @Query("select * from user")
    List<User> getUsers();

    @Query("select * from image")
    List<Image> getImages();

}
