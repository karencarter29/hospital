create table user_roles (
                            user_id VARCHAR(255) ,
                            role_id VARCHAR(255) ,
                            CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE,
                            CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id)
                                REFERENCES roles(id)
                                ON DELETE CASCADE
) engine=InnoDB

GO