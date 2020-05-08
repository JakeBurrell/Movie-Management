package com.github.jake_burrell.movie_management;


/**
 * Stores a collection of members who use the community library
 * @author Jake Burrell
 */

public class MemberCollection {

    private Member[] members;

    public MemberCollection() {
        this.members = new Member[0];
    }

    /**
     *  Register a Member
     * @param newMember to be added
     * @implNote only adds if member not already a member
     */
    public boolean registerMember(Member newMember) {
        String username = newMember.getUsername();
        if(memberExists(username)) return false;
        else {
            Member[] tmpMembers = members;
            this.members = new Member[tmpMembers.length + 1];
            for (int index = 0; index < tmpMembers.length; index++) {
                this.members[index] = tmpMembers[index];
            }
            this.members[members.length - 1] = newMember;
            return true;
        }
    }

    /**
     * Returns a member associated to a particular username.
     * @param username A username associated to a member within the MemberCollection.
     * @return The member associated to the username.
     */
    public Member getMember(String username) {
        Member retrievedMember = null;
        for (Member member : members) {
            if (member.getUsername().equals(username)) retrievedMember = member;
        }
        return retrievedMember;
    }

    /**
     * Checks if a member exists
     * @param username Username associated to user in memberCollection
     * @return Returns true if member exists with associated username with memberCollection.
     */
    public boolean memberExists(String username) {
        return getMember(username) != null;
    }

    public boolean checkPassword(String username, int password) {
        return memberExists(username) && (password == getPassword(username));
    }

    public int getPassword(String username) {
        Integer password = null;
        password = getMember(username).password;
        return password;
    }

    /**
     *  Find a member's contact phone number
     * @param username The member's username who's phone number is to be found
     * @return phoneNumber The member's phone number.
     * @throws NullPointerException If no phone number is found.
     */
    public Integer getPhoneNumber(String username) throws NullPointerException {
        Integer phoneNumber = null;
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                phoneNumber = member.phoneNumber;
            }
        }
        return phoneNumber;
    }

}
