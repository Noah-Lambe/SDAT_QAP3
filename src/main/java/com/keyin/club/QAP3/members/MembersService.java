package com.keyin.club.QAP3.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersRepository membersRepository;

    public List<Members> getAllMembers() {
        return (List<Members>) membersRepository.findAll();
    }

    public Members getMemberById(long id) {
        return membersRepository.findById(id).orElse(null);
    }

    public Members createMember(Members member) {
        return membersRepository.save(member);
    }

    public Members updateMember(long id, Members updatedMember) {
        return membersRepository.findById(id)
                .map(member -> {
                    member.setName(updatedMember.getName());
                    member.setPhoneNumber(updatedMember.getPhoneNumber());
                    member.setMembershipStartDate(updatedMember.getMembershipStartDate());
//                    member.setMembershipType(updatedMember.getMembershipType());
                    return membersRepository.save(member);
                })
                .orElseGet(() -> {
                    updatedMember.setId(id);
                    return membersRepository.save(updatedMember);
                });
    }

    public void deleteMember(long id) {
        membersRepository.deleteById(id);
    }

    public List<Members> findByNameContainingIgnoreCase(long tournamentId) {
        return membersRepository.findByNameContainingIgnoreCase(String.valueOf(tournamentId));
    }

    public List<Members> findByPhoneNumber(String phoneNumber) {
        return membersRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Members> findByMembershipStartDate(String startDate) {
        return membersRepository.findByMembershipStartDate(java.time.LocalDate.parse(startDate));
    }

    public List<Members> searchMembers(String name, String email, String phoneNumber) {
        if (name != null && !name.isEmpty()) {
            return membersRepository.findByNameContainingIgnoreCase(name);
        } else if (email != null && !email.isEmpty()) {
            return membersRepository.findByEmailContainingIgnoreCase(email);
        } else {
            return getAllMembers();
        }
    }

    public void deleteMemberById(long id) {
        membersRepository.deleteById(id);
    }

    // Additional methods for filtering by membership type can be added here
    // public List<Members> findByMembershipType(String type) {
    //     return membersRepository.findByMembershipType(type);
    // }
}
