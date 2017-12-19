package tropicraft.asm;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.relauncher.IClassTransformer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiIngameTransformer implements IClassTransformer {
	private String className = "net.minecraft.client.gui.GuiIngame";
	
	private String obfMethodName = "func_73830_a";
	private String mcpMethodName = "renderGameOverlay";
	
	private String methodDesc = "(FZII)V";
	
	private String pointOwner = "net/minecraft/client/renderer/EntityRenderer";
	
	private String obfPointName = "func_78478_c";
	private String mcpPointName = "setupOverlayRendering";
	
	private String pointDesc = "()V";
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (name.equals(className)) {
			return transformClass(bytes);
		}
		return bytes;
	}
	
	private byte[] transformClass(byte[] bytes) {
		ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        
        Iterator<MethodNode> methods = classNode.methods.iterator();
        
        while (methods.hasNext())
        {
            MethodNode m = methods.next();
            
            if((m.name.equals(mcpMethodName) || m.name.equals(obfMethodName)) &&
            		m.desc.equals(methodDesc)) {
            	transformMethod(m);
            	break;
            }
        }
        
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);		
        classNode.accept(cw);
        
        return cw.toByteArray();
	}

	protected void transformMethod(MethodNode method) {
		for (int index = 0; index < method.instructions.size(); index++) {
    		if(method.instructions.get(index).getType() == AbstractInsnNode.METHOD_INSN) {
    			MethodInsnNode mdNode = (MethodInsnNode) method.instructions.get(index);    			
    			
    			if (mdNode.owner.equals(pointOwner) &&
    					(mdNode.name.equals(mcpPointName) || mdNode.name.equals(obfPointName)) &&
    					mdNode.desc.equals(pointDesc)) {
    				System.out.println("Transforming GuiIngame");
    				InsnList toInject = new InsnList();

    				toInject.add(new VarInsnNode(Opcodes.FLOAD, 1));
    				toInject.add(new VarInsnNode(Opcodes.ILOAD, 2));
    				toInject.add(new VarInsnNode(Opcodes.ILOAD, 3));
    				toInject.add(new VarInsnNode(Opcodes.ILOAD, 4));
    				toInject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "tropicraft/CoreModMethods", "renderGameOverlay", "(FZII)V"));

    				method.instructions.insert(method.instructions.get(index), toInject);
    				break;
				}
    		}
		}
	}
}
