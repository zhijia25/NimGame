import java.util.ArrayList;

public class NimSort {

	public void sort(ArrayList<NimPlayer> userList) {
		for (int k = 0; k < userList.size(); k++) {
			for (int j = 0; j < userList.size() - 1 - k; j++) {
				if (userList.get(j).getUserName().compareToIgnoreCase(userList.get(j + 1).getUserName()) > 0) {

					NimPlayer temp = userList.get(j);
					userList.set(j, userList.get(j + 1));
					userList.set(j + 1, temp);
				}

			}
		}
	}

	public void ratioSortAsc(ArrayList<NimPlayer> userList) {

		// bubble sort in asc order
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < userList.size() - 1 - i; j++) {
				if (userList.get(j).getRatio() > userList.get(j + 1).getRatio()) {
					NimPlayer temp = userList.get(j);
					userList.set(j, userList.get(j + 1));
					userList.set(j + 1, temp);
				}
			}
		}

		// sort players have similar ratio
		for (int j = 0; j < userList.size(); j++) {
			for (int k = 0; k < userList.size() - 1 - j; k++) {
				if (userList.get(k).getRatio() == userList.get(k + 1).getRatio()
						&& !userList.get(k).getUserName().equals("default")
						&& !userList.get(k + 1).getUserName().equals("default")
						&& userList.get(k).getUserName().compareToIgnoreCase(userList.get(k + 1).getUserName()) > 0) {
					
					NimPlayer temp = userList.get(k);
					userList.set(k, userList.get(k + 1));
					userList.set(k + 1, temp);
				}
			}
		}
	}

	public void ratioSortDesc(ArrayList<NimPlayer> userList) {

		// bubble sort in desc order
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < userList.size() - 1 - i; j++) {
				if (userList.get(j).getRatio() < userList.get(j + 1).getRatio()) {

					NimPlayer temp = userList.get(j);
					userList.set(j, userList.get(j + 1));
					userList.set(j + 1, temp);

				}
			}
		}

		// sort players have similar ratio
		for (int j = 0; j < userList.size(); j++) {
			for (int k = 0; k < userList.size() - 1 - j; k++) {
				if (userList.get(k).getRatio() == userList.get(k + 1).getRatio()
						&& !userList.get(k).getUserName().equals("default")
						&& !userList.get(k + 1).getUserName().equals("default")
						&& userList.get(k).getUserName().compareToIgnoreCase(userList.get(k + 1).getUserName()) > 0) {

					NimPlayer temp = userList.get(k);
					userList.set(k, userList.get(k + 1));
					userList.set(k + 1, temp);

				}
			}
		}

	}
}