package com.hazelcast.hibernate.distributed.entity;

import java.io.IOException;
import java.util.Set;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

public class Test  implements AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 200916307235587720L;
	private Long id;
	private Long version;
	private String testId;
	private Long productId;
	private String testName;
	private Set<TestLocation> testlocations;
	private Set<Location> locations;
		

	/*@Override
	public void readData(ObjectDataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeData(ObjectDataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
	*/
	public Test(){}

	public Test(String testId, Long productId, String testName, Set<TestLocation> testlocations,
			Set<Location> locations) {
		super();
		this.testId = testId;
		this.productId = productId;
		this.testName = testName;
		this.testlocations = testlocations;
		this.locations = locations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((testId == null) ? 0 : testId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (testId == null) {
			if (other.testId != null)
				return false;
		} else if (!testId.equals(other.testId))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Set<TestLocation> getTestlocations() {
		return testlocations;
	}

	public void setTestlocations(Set<TestLocation> testlocations) {
		this.testlocations = testlocations;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
	

	
	
	

}
