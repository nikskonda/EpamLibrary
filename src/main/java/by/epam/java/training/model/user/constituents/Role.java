package by.epam.java.training.model.user.constituents;

import java.io.Serializable;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Role(){
    }

    public Role(String name) {
        this.id = 0;
        this.name = name;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        if (id == null){
            if (role.id != null) return false;
        }
        else if (!id.equals(role.id)) return false;
        if (name == null){
            if (role.name != null) return false;
        }
        else if (!name.equals(role.name)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        hashCode = prime * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = prime * hashCode + (name != null ? name.hashCode() : 0);
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{id=").append(id).append(", name='").append(name).append("'}");
        return sb.toString();
    }
}

