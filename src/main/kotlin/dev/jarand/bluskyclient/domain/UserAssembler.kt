package dev.jarand.bluskyclient.domain

import dev.jarand.bluskyclient.resource.CreateSessionTokenResource

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
