package dev.jarand.bluskyclient.user.domain

import dev.jarand.bluskyclient.authentication.resource.CreateSessionTokenResource

class UserAssembler {

    fun assemble(resource: CreateSessionTokenResource): User {
        return User(
            did = resource.did,
            handle = resource.handle,
            email = resource.email,
            accessJwt = resource.accessJwt,
            refreshJwt = resource.refreshJwt
        )
    }
}
