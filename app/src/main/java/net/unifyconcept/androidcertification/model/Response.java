package net.unifyconcept.androidcertification.model;

/**
 * Created by Olabode Qudus on 11/3/2018.
 */
import net.unifyconcept.androidcertification.model.GitUser;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private List<GitUser> gituser = new ArrayList<GitUser>();

    public List<GitUser> getUsers() {
        return gituser;
    }
}
