package com.descodeuses.planit.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.descodeuses.planit.dto.ActionDTO;
import com.descodeuses.planit.entity.ActionEntity;
import com.descodeuses.planit.entity.ContactEntity;
import com.descodeuses.planit.entity.UserEntity;
import com.descodeuses.planit.repository.ActionRepository;
import com.descodeuses.planit.repository.ContactRepository;
import com.descodeuses.planit.repository.ProjectRepository;
import com.descodeuses.planit.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActionService {
    private final ActionRepository repository;
    private final ContactRepository contactRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ActionService(
            ActionRepository repository,
            ContactRepository contactRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.repository = repository;
        this.contactRepository = contactRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // convertToDto transforme une entité Action en ActionDTO.
    private ActionDTO convertToDto(ActionEntity action) {
        ActionDTO dto = new ActionDTO(
                action.getId(),
                action.getTitle(),
                action.getCompleted(),
                action.getDueDate(),
                action.getPriority());
        // ajout membres
  /*       Set<Long> memberIds = action.getMembers().stream()
                .map(ContactEntity::getId)
                .collect(Collectors.toSet()); */

        //dto.setMemberIds(memberIds);

        // ajout projet
        if (action.getProject() != null) {
            dto.setProjectId(action.getProject().getId());
        }
        // userConnected
        //if (action.getUser() != null) {
        //    dto.setUser(action.getUser());
        //}
        return dto;
    }

    // convertoEntity transformer un ActionDTO en Action (l’entité à enregistrer en
    // base).
    private ActionEntity convertToEntity(ActionDTO actionDTO, Set<ContactEntity> members) {
        ActionEntity action = new ActionEntity();
        action.setId(actionDTO.getId());
        action.setTitle(actionDTO.getTitle());
        action.setCompleted(actionDTO.getCompleted());
        action.setDueDate(actionDTO.getDueDate());
        action.setPriority(actionDTO.getPriority());
        action.setMembers(members);

        if (actionDTO.getProjectId() != null) {
            action.setProject(projectRepository.findById(actionDTO.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Project not found with id: " + actionDTO.getProjectId())));
        }

/*         if (actionDTO.getUserConnectedId() != null) {
            action.setUserConnected(userRepository.findById(actionDTO.getUserConnectedId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "User not found with id: " + actionDTO.getUserConnectedId())));
        } */
        return action;
    }

    // ******** get ALL actions
    public List<ActionDTO> getAll() {
        // ******* générer liste action dto depuis liste action
        List<ActionEntity> actionslist = repository.findAll(); // liste entités Action
        List<ActionDTO> actionDTOsList = new ArrayList<>(); // liste Dtos à retourner

        for (ActionEntity item : actionslist) {
            actionDTOsList.add(convertToDto(item)); // ajoute action convertit en DTO à la liste actionsDtos
        }
        return actionDTOsList; // retourne la nouvelle liste
    }

    // ******** get 1 action par l'iD
    public ActionDTO getActionById(Long id) {
        Optional<ActionEntity> actionById = repository.findById(id);
        if (actionById.isEmpty()) { // contraire: .isPresent()
            throw new EntityNotFoundException("Action not found with id: " + id);
            // si id n'existe pas, lance une exception
        }
        return convertToDto(actionById.get());
    }

    // ******** créer 1 action
    public ActionDTO create(ActionDTO dto) {
        //Set<ContactEntity> contacts = new HashSet<>(contactRepository.findAllById(dto.getMemberIds()));
        ActionEntity entity = convertToEntity(dto, null); // Convertir le DTO en entité

        // userConnected
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String username = userDetails.getUsername();

        //UserEntity user = userRepository.findByUsername(username)
        //        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //entity.setUserConnected(user);

        ActionEntity savedEntity = repository.save(entity); // Sauvegarder l'entité dans la base de données
        return convertToDto(savedEntity); // Convertir l'entité enregistrée en DTO et retourner
    }

    // ******* mettre à jour une action
    public ActionDTO update(Long id, ActionDTO dto) {
        Optional<ActionEntity> existEntity = repository.findById(id); // entitéExistante = référentiel.trouverParId(id)
        if (existEntity.isEmpty()) { // sinon lever une exception "Ressource non trouvée"
            throw new EntityNotFoundException("Action not found with id: " + id);
        }
        ActionEntity entity = existEntity.get(); // extraire l'entité de l'Optional

        entity.setTitle(dto.getTitle());
        entity.setCompleted(dto.getCompleted());
        entity.setDueDate(dto.getDueDate());
        entity.setPriority(dto.getPriority());

        // membres
        Set<ContactEntity> contacts = new HashSet<>(contactRepository.findAllById(dto.getMemberIds()));

        entity.setMembers(contacts);
        // projet
        if (dto.getProjectId() != null) {
            entity.setProject(projectRepository.findById(dto.getProjectId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Project not found with id: " + dto.getProjectId())));
        }
        // userConnected
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //entity.setUserConnected(user);

        ActionEntity updatedAction = repository.save(entity);

        return convertToDto(updatedAction);
    }

    // ****** supprimer une action
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Action not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // **** filtrer liste par session user
 /*    public List<ActionDTO> getTodosByUserConnected(Long userId) {
        List<ActionEntity> todos = repository.findByUserConnectedId(userId);
        return todos.stream().map(this::convertToDto).collect(Collectors.toList());
    } */
}
