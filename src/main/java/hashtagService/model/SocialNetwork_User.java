package hashtagService.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIALNETWORK_USER")
public class SocialNetwork_User implements Serializable {	
	private static final long serialVersionUID = 4004890157830310261L;
	
	private User user;
	private SocialNetwork socialNetwork;
	private String userName;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "SOCIALNETWORK_ID")
	public SocialNetwork getSocialNetwork() {
		return socialNetwork;
	}

	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    @Override
    public int hashCode() {
        int result;
        result = (int)(user.getId()/17 + socialNetwork.getId()/17);
        return result;
    }
	
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SocialNetwork_User)) {
            return false;
        }
        
        if (o == this) {
        	return true;
        }

        SocialNetwork_User otherSocialNetwork_User = (SocialNetwork_User) o;
        
        //Objects.equals contemplates the case where both parameters are null, returning true when that happens
        return
        	Objects.equals(user, otherSocialNetwork_User.user) &&
            Objects.equals(socialNetwork, otherSocialNetwork_User.socialNetwork) &&
            Objects.equals(userName, otherSocialNetwork_User.userName);
    }
}
