package com.daniel.common.network;

import com.daniel.common.person.Person;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private static final long serialVersionUID = -4795471465018385318L;

    private final RequestType type;
    private final String command;
    private final Integer arg;
    private final Person person;

    public Request(RequestType type, String command, Integer arg, Person person) {
        this.type = type;
        this.command = command;
        this.arg = arg;
        this.person = person;
    }

    public RequestType getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }

    public Integer getArg() {
        return arg;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (type != request.type) return false;
        if (!command.equals(request.command)) return false;
        if (!Objects.equals(arg, request.arg)) return false;
        return Objects.equals(person, request.person);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + command.hashCode();
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "type=" + type +
                ", command='" + command + '\'' +
                ", arg='" + arg + '\'' +
                ", person=" + person +
                '}';
    }
}
