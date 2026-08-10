package com.pvelilla.backend.hairapp.HairApp.service.impl;

import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Profile;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProfileServiceImpl profileService;

    private Profile profile;
    private ProfileDTO profileDTO;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setProfileId(1L);
        profile.setProfileName("ADMIN");

        profileDTO = new ProfileDTO();
        profileDTO.setProfileId(1L);
        profileDTO.setProfileName("ADMIN");
    }

    @Test
    void findAll_ShouldReturnListOfProfileDTO() {
        when(profileRepository.findAll()).thenReturn(Arrays.asList(profile));
        when(modelMapper.map(any(Profile.class), eq(ProfileDTO.class))).thenReturn(profileDTO);

        List<ProfileDTO> result = profileService.findAll(Optional.empty());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getProfileName()).isEqualTo(profileDTO.getProfileName());
        verify(profileRepository).findAll();
    }

    @Test
    void findById_WhenProfileExists_ShouldReturnProfileDTO() {
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(modelMapper.map(profile, ProfileDTO.class)).thenReturn(profileDTO);

        ProfileDTO result = profileService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getProfileId()).isEqualTo(1L);
        verify(profileRepository).findById(1L);
    }

    @Test
    void findById_WhenProfileDoesNotExist_ShouldThrowRecordNotFoundException() {
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> profileService.findById(1L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void save_ShouldReturnProfileId() {
        when(modelMapper.map(profileDTO, Profile.class)).thenReturn(profile);
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Long result = profileService.save(profileDTO);

        assertThat(result).isEqualTo(1L);
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void update_WhenProfileExists_ShouldReturnUpdatedProfileDTO() {
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(modelMapper.map(profileDTO, Profile.class)).thenReturn(profile);
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);
        when(modelMapper.map(any(Profile.class), eq(ProfileDTO.class))).thenReturn(profileDTO);

        ProfileDTO result = profileService.update(1L, profileDTO);

        assertThat(result).isNotNull();
        assertThat(result.getProfileId()).isEqualTo(1L);
        verify(profileRepository).findById(1L);
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void deleteById_WhenProfileExists_ShouldReturnDeletedProfileDTO() {
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(modelMapper.map(profile, ProfileDTO.class)).thenReturn(profileDTO);

        ProfileDTO result = profileService.deleteById(1L);

        assertThat(result).isNotNull();
        verify(profileRepository).findById(1L);
        verify(profileRepository).delete(profile);
    }
}
