package com.example.springpoll.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
	boolean isAdmin;
	String discord_nickname;
	String discord_id;
	String avatar;
}
