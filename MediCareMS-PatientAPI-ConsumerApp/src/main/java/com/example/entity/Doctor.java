package com.example.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Doctor
{
	@Id
	@SequenceGenerator(name = "seq" ,sequenceName ="doctor_seq",initialValue = 500,allocationSize = 1)
	@GeneratedValue(generator = "seq",strategy = GenerationType.SEQUENCE)
	 private Integer id;
	
	@NonNull
	@Column(length = 35)
	 private String name;
	
	@NonNull
	@Column(length = 35)
	 private String specialization;
	
	@NonNull
	 private Integer experience;
	
	@NonNull
	private Long contactno;
	
	@NonNull
	@Column(length = 50)
	 private String email;
	
	
	
	//metadata properites
	 @Version
	  private  Integer  updateCount;
	 
	  @CreationTimestamp
	  @Column(updatable = false)
	  private   LocalDateTime  createdOn;
	  
	  @UpdateTimestamp
	  @Column(insertable = false)
	  private   LocalDateTime  lastUpdatedOn;
	  
	  @Column(length=30,updatable = false)
	  private String createdBy;
	  
	  @Column(length=30,insertable = false)
	  private String updatedBy;
}
