
package neu.edu.csye6200.model;

public class Rules {
	
    private final int minAge;
    private final int maxAge;
    private final int groupSize;
    private final String ratio;
    private final int maxGroup;
    private final int size;

    public Rules(int minAge, int maxAge, int groupSize, String ratio, int maxGroup) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.groupSize = groupSize;
        this.ratio = ratio;
        this.maxGroup = maxGroup;
        this.size = groupSize*maxGroup;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getRatio() {
        return ratio;
    }

    public int getMaxGroup() {
        return maxGroup;
    }

    public int getSize() {
        return size;
    }
    
	
    
	@Override
	public String toString() {
		return "Rules [minAge=" + minAge + ", maxAge=" + maxAge + ", groupSize=" + groupSize
				+ ", ratio=" + ratio + ", maxGroup=" + maxGroup + "]";
	}
	
	
}
