package ua.rd.yodabot.domain;


public class Task extends DomainObject implements Cloneable{

    private String description;
    private String listName;

    public Task() {
    }

    public Task(Long id, String description, String listName) {
        setId(id);
        this.description = description;
        this.listName = listName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }


    @Override
    public int hashCode() {
        return getId().intValue();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Task task = (Task) o;
//
//        return task.getId().equals(getId());
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;
        if (getId() != task.getId()) {
            return false;
        }

        if (!getDescription().equals(task.getDescription())) return false;
        return getListName().equals(task.getListName());

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", listName='" + listName + '\'' +
                '}';
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
