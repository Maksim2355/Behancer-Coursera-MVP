package com.lumi.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lumi.domain.model.project.Cover;
import com.lumi.domain.model.project.Owner;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.model.user.Image;
import com.lumi.domain.model.user.User;


@Database(entities = {Project.class, Cover.class, Owner.class, User.class, Image.class}, version = 1)
public abstract class BehanceDatabase extends RoomDatabase {

    public abstract BehanceDao getBehanceDao();
}
