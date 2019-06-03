package library.domain.repository;

import library.configuration.ConnectionFactory;
import library.domain.entity.Candidate;
import library.services.AuditService;
import library.tools.DataExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class CandidateRepositoryDBImpl implements CandidateRepository
{
    AuditService auditService = AuditService.getInstance();
    private static final String GET_ALL_CANDIDATES = "SELECT * FROM candidates";
    private static final String CREATE_NEW_CANDIDATE = "INSERT INTO candidates VALUES (?,?,?,?,?,?,?,?)";

    public List<Candidate> getCandidates()
    {
        auditService.printAudit("getCandidatesDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_CANDIDATES);
            return DataExtractor.extractCandidatesFrom(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void addCandidate(Candidate candidate) {
        auditService.printAudit("addCandidateDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_NEW_CANDIDATE);
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getCNP());
            stmt.setInt(3, candidate.getId());
            stmt.setDouble(4, candidate.getBacGrade().getRomGrade());
            stmt.setDouble(5, candidate.getBacGrade().getMathGrade());
            stmt.setDouble(6, candidate.getBacGrade().getInfoGrade());
            stmt.setString(7, candidate.getEmail());
            stmt.setString(8, candidate.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addCandidate(String name, String CNP, int id, double romGrade, double mathGrade, double infoGrade, String email, String phoneNumber) {
        auditService.printAudit("addCandidateDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_NEW_CANDIDATE);
            stmt.setString(1, name);
            stmt.setString(2, CNP);
            stmt.setInt(3, id);
            stmt.setDouble(4, romGrade);
            stmt.setDouble(5, mathGrade);
            stmt.setDouble(6, infoGrade);
            stmt.setString(7, email);
            stmt.setString(8, phoneNumber);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void readCandidatesFromFile(String fileName){
        auditService.printAudit("readCandidatesFromFileDB");
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Candidate newCandidate = new Candidate(values[0], values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Integer.parseInt(values[5]), values[6], values[7]);
                addCandidate(newCandidate);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
