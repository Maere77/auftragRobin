package com.axa.ch.its.versicherungpersitance;

import com.axa.ch.its.versicherungpersitance.domain.Group;
import com.axa.ch.its.versicherungpersitance.domain.Matchh;
import com.axa.ch.its.versicherungpersitance.domain.Player;
import com.axa.ch.its.versicherungpersitance.domain.Stadium;
import com.axa.ch.its.versicherungpersitance.domain.Team;
import com.axa.ch.its.versicherungpersitance.repository.GroupRepository;
import com.axa.ch.its.versicherungpersitance.repository.MatchRepository;
import com.axa.ch.its.versicherungpersitance.repository.PlayerRepository;
import com.axa.ch.its.versicherungpersitance.repository.StadiumRepository;
import com.axa.ch.its.versicherungpersitance.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Dataloader implements ApplicationRunner {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private TeamRepository teamRepository;

    private static final List<String> POSITIONS = Arrays.asList(
            "Goalkeeper", "Defender", "Midfielder", "Forward",
            "Left Back", "Right Back", "Center Back",
            "Defensive Midfielder", "Attacking Midfielder",
            "Left Winger", "Right Winger", "Striker"
    );

    public static String getRandomPosition() {
        Random random = new Random();
        int index = random.nextInt(POSITIONS.size());
        return POSITIONS.get(index);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Group> groups = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<Stadium> stadions = new ArrayList<>();

        for (int i = 1; i < 7; i++) {
            Group group = new Group();
            group.setGroupName("Group " + i);
            groupRepository.save(group);
            groups.add(group);

            for (int j = 1; j < 5; j++) {
                Team team = new Team();
                team.setName("Group " + i + " Team " + j);
                team.setGroupp(group);
                team.setCoach("Coach " + i);
                teamRepository.save(team);
                teams.add(team);

                for (int k = 1; k < 27; k++) {
                    Player player = new Player();
                    player.setName("Player " + k + "( Group " + i + " Team " + j + " )");
                    player.setTeam(team);
                    player.setPosition(getRandomPosition());
                    player.setAge(new java.util.Random().nextInt(16) + 20);
                    playerRepository.save(player);
                }
            }
        }

        for (int i = 1; i < 7; i++) {
            Stadium stadion = new Stadium();
            stadion.setStadiumName("Stadion " + i);
            stadion.setPlace("Stadion City " + i);
            stadiumRepository.save(stadion);
            stadions.add(stadion);
        }

        for (Group group : groups) {
            List<Team> groupTeams = teams.stream().filter(team -> team.getGroupp().equals(group)).collect(Collectors.toList());
            Stadium stadion = stadions.get(groups.indexOf(group)); // Jedem Stadion eine Gruppe zuweisen
            for (int i = 0; i < groupTeams.size(); i++) {
                for (int j = i + 1; j < groupTeams.size(); j++) {
                    Matchh matchh = new Matchh();
                    matchh.setStadium(stadion);
                    Set<Team> matchTeams = new HashSet<>(Arrays.asList(groupTeams.get(i), groupTeams.get(j)));
                    matchh.setTeams(matchTeams);
                    matchRepository.save(matchh);
                }
            }
        }
    }
}

    /*
    @Override
    public void run(ApplicationArguments args) {
        // Create and save Stadiums
        Stadium stadiumA = new Stadium();
        stadiumA.setPlace("Turin");
        stadiumA.setStadiumName("Allianz Stadium");

        Stadium stadiumB = new Stadium();
        stadiumB.setPlace("Barcelona");
        stadiumB.setStadiumName("Camp Nou");

        stadiumRepository.saveAll(List.of(stadiumA, stadiumB));

        // Create and save Groups
        Group groupA = new Group();
        groupA.setGroupName("Group A");

        Group groupB = new Group();
        groupB.setGroupName("Group B");

        groupRepository.saveAll(List.of(groupA, groupB));

        // Create and save Teams for Group A
        List<Team> teamsGroupA = createTeamsForGroup(groupA, Arrays.asList("Juventus", "Barcelona", "PSG", "Liverpool"));
        teamRepository.saveAll(teamsGroupA);

        // Create and save Teams for Group B
        List<Team> teamsGroupB = createTeamsForGroup(groupB, Arrays.asList("Real Madrid", "Manchester United", "Bayern Munich", "Chelsea"));
        teamRepository.saveAll(teamsGroupB);

        // Create and save Players
        List<Player> players = new ArrayList<>();
        for (Team team : teamsGroupA) {
            players.addAll(createPlayersForTeam(team));
        }
        for (Team team : teamsGroupB) {
            players.addAll(createPlayersForTeam(team));
        }
        playerRepository.saveAll(players);

        // Create and save Matches for Group A
        List<Matchh> matchesGroupA = createMatchesForGroup(teamsGroupA, stadiumA);
        matchRepository.saveAll(matchesGroupA);

        // Create and save Matches for Group B
        List<Matchh> matchesGroupB = createMatchesForGroup(teamsGroupB, stadiumB);
        matchRepository.saveAll(matchesGroupB);
    }

    private List<Team> createTeamsForGroup(Group group, List<String> teamNames) {
        List<Team> teams = new ArrayList<>();
        for (String teamName : teamNames) {
            Team team = new Team();
            team.setName(teamName);
            team.setCoach("Coach of " + teamName);
            team.setGroupp(group);
            teams.add(team);
        }
        return teams;
    }

    private List<Player> createPlayersForTeam(Team team) {
        List<String> playerNames = Arrays.asList(
                "Cristiano Ronaldo", "Lionel Messi", "Neymar Jr", "Kylian Mbappe", "Kevin De Bruyne",
                "Mohamed Salah", "Virgil van Dijk", "Sergio Ramos", "Robert Lewandowski", "Luka Modric",
                "Harry Kane", "Paul Pogba", "Eden Hazard", "Sadio Mane", "Raheem Sterling",
                "Romelu Lukaku", "Antoine Griezmann", "Karim Benzema", "N'Golo Kante", "Marco Verratti",
                "Gerard Pique", "Sergio Aguero", "Thiago Silva", "Jan Oblak", "David de Gea", "Alisson Becker"
        );
        List<Player> players = new ArrayList<>();
        Random random = new Random();
        int numberOfPlayers = random.nextInt(16) + 11; // Between 11 and 26 players

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            player.setName(playerNames.get(i % playerNames.size()) + " (" + team.getName() + ")");
            player.setPosition(getRandomPosition());
            player.setAge(random.nextInt(23) + 18); // zwischen 18 & 40
            player.setTeam(team);
            players.add(player);
        }
        return players;
    }

    // Method to generate random football positions
    private String getRandomPosition() {
        List<String> positions = Arrays.asList(
                "Goalkeeper", "Defender", "Midfielder", "Forward",
                "Left Back", "Right Back", "Center Back",
                "Defensive Midfielder", "Attacking Midfielder",
                "Left Winger", "Right Winger", "Striker"
        );
        Random random = new Random();
        int index = random.nextInt(positions.size());
        return positions.get(index);
    }

    private Team findTeamByName(String name, List<Team> teams) {
        return teams.stream()
                .filter(team -> team.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private List<Matchh> createMatchesForGroup(List<Team> teams, Stadium stadium) {
        List<Matchh> matches = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Matchh match = new Matchh();
                match.setWinner(teams.get(random.nextInt(teams.size())).getName());
                match.setStadium(stadium);
                match.getTeams().add(teams.get(i));
                match.getTeams().add(teams.get(j));
                matches.add(match);
            }
        }
        return matches;
    }

     */