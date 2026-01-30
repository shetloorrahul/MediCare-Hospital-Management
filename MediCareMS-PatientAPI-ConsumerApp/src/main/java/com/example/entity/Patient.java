package com.example.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient
{
	@Id
	@SequenceGenerator(name = "seq",sequenceName = "patient_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "seq",strategy = GenerationType.SEQUENCE)
	private Integer id;
	@NonNull
	@Column(length=35)
    private String name;
	@NonNull
    private Integer age;
	@NonNull
	@Column(length=10)
    private String gender;
	@NonNull
    private Long contactno;
	@NonNull
	@Column(length=50)
    private String address;
	@NonNull
	@Column(length=100)
    private String problem;
	
	
	@NonNull
	@ManyToOne(targetEntity = Doctor.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "doctorId",referencedColumnName = "id")//for Fk Column
	private Doctor doctor;
	
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

		@Override
		public String toString() {
			return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", contactNo="
					+ contactno + ", address=" + address + ", problem=" + problem + "]";
		}


}
