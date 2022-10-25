package CD_Seller;

public class DiskBook {
	private Disk[] data = new Disk[1000];

	public void print() {
		for (Disk disk : data) {
			if (disk != null) {
				System.out.println(disk);
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (Disk disk : data) {
			if (disk != null) {
				result += disk + "\n";
			}
		}
		return result;
	}

	public Disk findDisk(int id) {
		return data[id];
	}

	public void addDisk(Disk d) {
		int id = d.getId();
		Disk disk = findDisk(id);
		if (disk == null) {
			data[id] = d;
		} else {
			int num = disk.getNum();
			num += d.getNum();
			disk.setNum(num);
		}
	}

	public void removeDisk(Disk d) {
		int id = d.getId();
		Disk disk = findDisk(id);
		if (disk == null) {
			return;
		} else {
			disk.setNum(disk.getNum() - 1);
		}
	}
}
