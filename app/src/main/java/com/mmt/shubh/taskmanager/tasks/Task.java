package com.mmt.shubh.taskmanager.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import com.mmt.shubh.taskmanager.TaskStatus;

public class Task implements Parcelable {

    private String mTitle;

    private String mDescription;

    private long mId;

    private long mStartDate;

    private long mEndDate;

    private TaskStatus mTaskStatus;

    protected Task(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mTaskStatus = TaskStatus.valueOf(in.readString());
        mId = in.readLong();
        mStartDate = in.readLong();
        mEndDate = in.readLong();
    }

    public Task() {
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getDescription() {
        return mDescription;
    }

    public Task setDescription(String description) {
        mDescription = description;
        return this;
    }

    public long getEndDate() {
        return mEndDate;
    }

    public Task setEndDate(long endDate) {
        mEndDate = endDate;
        return this;
    }

    public long getId() {
        return mId;
    }

    public Task setId(long id) {
        mId = id;
        return this;
    }

    public long getStartDate() {
        return mStartDate;
    }

    public Task setStartDate(long startDate) {
        mStartDate = startDate;
        return this;
    }

    public TaskStatus getTaskStatus() {
        return mTaskStatus;
    }

    public Task setTaskStatus(TaskStatus taskStatus) {
        mTaskStatus = taskStatus;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public Task setTitle(String title) {
        mTitle = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (getId() != task.getId()) return false;
        if (getStartDate() != task.getStartDate()) return false;
        if (getEndDate() != task.getEndDate()) return false;
        if (!getTitle().equals(task.getTitle())) return false;
        if (!getDescription().equals(task.getDescription())) return false;
        return getTaskStatus() == task.getTaskStatus();

    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getStartDate() ^ (getStartDate() >>> 32));
        result = 31 * result + (int) (getEndDate() ^ (getEndDate() >>> 32));
        result = 31 * result + getTaskStatus().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "mDescription='" + mDescription + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mId=" + mId +
                ", mStartDate=" + mStartDate +
                ", mEndDate=" + mEndDate +
                ", mTaskStatus=" + mTaskStatus +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mTaskStatus.name());
        dest.writeLong(mId);
        dest.writeLong(mStartDate);
        dest.writeLong(mEndDate);
    }
}