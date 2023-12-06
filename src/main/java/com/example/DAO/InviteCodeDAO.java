package com.example.DAO;

public class InviteCodeDAO {
    private Integer inviteCodeId;
    private String code;
    private String identity;

    public Integer getInviteCodeId() {
        return inviteCodeId;
    }

    public void setInviteCodeId(Integer inviteCodeId) {
        this.inviteCodeId = inviteCodeId;
    }

    public String getInviteCode() {
        return code;
    }

    public void setInviteCode(String inviteCode) {
        this.code = inviteCode;
    }

    public String getInviteIdentity() {
        return identity;
    }

    public void setInviteIdentity(String inviteIdentity) {
        this.identity = inviteIdentity;
    }

    public InviteCodeDAO(Integer inviteCodeId, String inviteCode, String inviteIdentity) {
        this.inviteCodeId = inviteCodeId;
        this.code = inviteCode;
        this.identity = inviteIdentity;
    }

    public InviteCodeDAO() {
    }
}
