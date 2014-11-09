package cl.uchile.dcc.feedback.cassandra.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import com.google.common.base.Objects;

@Table(value="user_tag_session")
public class UserTagSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private Integer userId;
	
	@PrimaryKeyColumn(name = "tag_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private Integer tagId;
	
	@Column(value="date")
	private Date date;	

	 public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof UserTagSession){
			UserTagSession that=(UserTagSession)other;
			return Objects.equal(this.userId, that.userId) &&
					Objects.equal(this.tagId, that.tagId);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.userId,this.tagId);
	}

}
