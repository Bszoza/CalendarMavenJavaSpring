package org.example.calendarmavenjavaspring.dto;

public class UserDTO {
    private String nickname;
    private String email;

    public UserDTO(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
