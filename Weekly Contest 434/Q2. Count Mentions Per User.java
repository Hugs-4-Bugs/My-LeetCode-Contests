import java.util.*;

class Event {
    int timestamp;
    int userId;

    Event(int timestamp, int userId) {
        this.timestamp = timestamp;
        this.userId = userId;
    }
}

class Solution {
    public int[] countMentions(int totalUsers, List<List<String>> eventLogs) {
        // Sort events based on timestamp
        eventLogs.sort((a, b) -> Integer.compare(Integer.parseInt(a.get(1)), Integer.parseInt(b.get(1))));

        // Initialize mentions and online status arrays
        int[] mentionCount = new int[totalUsers];
        boolean[] isOnline = new boolean[totalUsers];
        Arrays.fill(isOnline, true);

        // Priority queue to manage users coming online
        PriorityQueue<Event> onlineQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.timestamp, b.timestamp));

        int totalEvents = eventLogs.size();
        int index = 0;

        // Process each event
        while (index < totalEvents) {
            int currentTimestamp = Integer.parseInt(eventLogs.get(index).get(1));

            // Process users who should be online by the current timestamp
            while (!onlineQueue.isEmpty() && onlineQueue.peek().timestamp <= currentTimestamp) {
                int userToActivate = onlineQueue.poll().userId;
                if (userToActivate >= 0 && userToActivate < totalUsers) {
                    isOnline[userToActivate] = true;
                }
            }

            int nextIndex = index;
            // Group all events with the same timestamp together
            while (nextIndex < totalEvents && Integer.parseInt(eventLogs.get(nextIndex).get(1)) == currentTimestamp) {
                nextIndex++;
            }

            // Handle "OFFLINE" events
            for (int eventIndex = index; eventIndex < nextIndex; ++eventIndex) {
                if (eventLogs.get(eventIndex).get(0).equals("OFFLINE")) {
                    int userId = Integer.parseInt(eventLogs.get(eventIndex).get(2));
                    if (userId >= 0 && userId < totalUsers && isOnline[userId]) {
                        isOnline[userId] = false;
                        onlineQueue.offer(new Event(currentTimestamp + 60, userId));
                    }
                }
            }

            // Handle "MESSAGE" events
            for (int eventIndex = index; eventIndex < nextIndex; ++eventIndex) {
                if (eventLogs.get(eventIndex).get(0).equals("MESSAGE")) {
                    String mentionedUsers = eventLogs.get(eventIndex).get(2);

                    // Handle "ALL" mentions
                    if (mentionedUsers.equals("ALL")) {
                        for (int user = 0; user < totalUsers; ++user) {
                            mentionCount[user]++;
                        }
                    }
                    // Handle "HERE" mentions
                    else if (mentionedUsers.equals("HERE")) {
                        for (int user = 0; user < totalUsers; ++user) {
                            if (isOnline[user]) {
                                mentionCount[user]++;
                            }
                        }
                    }
                    // Handle specific user mentions
                    else {
                        String[] tokens = mentionedUsers.split(" ");
                        for (String token : tokens) {
                            if (token.startsWith("id")) {
                                String idStr = token.substring(2);
                                boolean isValid = true;
                                for (char c : idStr.toCharArray()) {
                                    if (!Character.isDigit(c)) {
                                        isValid = false;
                                        break;
                                    }
                                }
                                if (isValid) {
                                    int userId = Integer.parseInt(idStr);
                                    if (userId >= 0 && userId < totalUsers) {
                                        mentionCount[userId]++;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Move to the next set of events with a different timestamp
            index = nextIndex;
        }

        return mentionCount;
    }
}
