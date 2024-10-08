package viet.io.chirpchirp.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import viet.io.chirpchirp.security.dto.AuthUser;
import viet.io.chirpchirp.security.dto.RegRequest;
import viet.io.chirpchirp.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToUser(RegRequest registrationRequest);

    AuthUser convertToAuthUser(User user);

    User convertToUser(AuthUser authUser);

}
